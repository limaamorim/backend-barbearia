import prisma from "../prisma.js";

const agendamentoService = {

  async criar(dto) {

    const servico = await prisma.servico.findUnique({
      where: { id: dto.servicoId }
    });

    if (!servico) {
      throw new Error("Serviço não encontrado");
    }

    // converter data corretamente
    const dataFormatada = new Date(`${dto.data}T00:00:00`);

    // converter horaInicio corretamente
    const horaInicioDate = new Date(`1970-01-01T${dto.horaInicio}:00`);

    // calcular horaFim
    const [hora, minuto] = dto.horaInicio.split(":").map(Number);
    const inicioMinutos = hora * 60 + minuto;
    const fimMinutos = inicioMinutos + servico.duracaoMinutos;

    const horaFimDate = new Date(
      `1970-01-01T${String(Math.floor(fimMinutos / 60)).padStart(2, "0")}:${String(fimMinutos % 60).padStart(2, "0")}:00`
    );

    // verificar conflito
    const existe = await prisma.agendamento.findFirst({
      where: {
        data: dataFormatada,
        horaInicio: horaInicioDate
      }
    });

    if (existe) {
      throw new Error("Horário já ocupado");
    }

    return await prisma.agendamento.create({
      data: {
        nomeCliente: dto.nomeCliente,
        telefoneCliente: dto.telefoneCliente,
        data: dataFormatada,
        horaInicio: horaInicioDate,
        horaFim: horaFimDate,
        status: "AGENDADO",
        servicoId: dto.servicoId
      }
    });
  },
  async listarPorData(data) {

    const dataFormatada = new Date(`${data}T00:00:00`);

    return await prisma.agendamento.findMany({
      where: {
        data: dataFormatada
      },
      include: { servico: true }
    });
  },


  async cancelar(id) {
    return await prisma.agendamento.update({
      where: { id: Number(id) },
      data: { status: "CANCELADO" }
    });
  },

  async confirmar(id) {
    return await prisma.agendamento.update({
      where: { id: Number(id) },
      data: { status: "CONFIRMADO" }
    });
  }

};

export default agendamentoService;