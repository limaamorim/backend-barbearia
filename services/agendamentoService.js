import prisma from "../prisma.js";

const agendamentoService = {

  async criar(dto) {

    const dataHoraInicio = new Date(`${dto.data}T${dto.horaInicio}:00`);

    if (isNaN(dataHoraInicio)) {
      throw new Error("Data ou hora inválida");
    }

    const existe = await prisma.agendamento.findFirst({
      where: {
        horaInicio: dataHoraInicio
      }
    });

    if (existe) {
      throw new Error("Horário já ocupado");
    }

    const servico = await prisma.servico.findUnique({
      where: { id: dto.servicoId }
    });

    if (!servico) {
      throw new Error("Serviço não encontrado");
    }

    const horaFim = new Date(
      dataHoraInicio.getTime() + servico.duracaoMinutos * 60000
    );

    return await prisma.agendamento.create({
      data: {
        nomeCliente: dto.nomeCliente,
        telefoneCliente: dto.telefoneCliente,
        data: new Date(dto.data),
        horaInicio: dataHoraInicio,
        horaFim,
        status: "AGENDADO",
        servicoId: dto.servicoId
      }
    });
  },
  async listarPorData(data) {
    return await prisma.agendamento.findMany({
      where: {
        data: new Date(data)
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