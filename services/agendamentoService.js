import prisma from "../prisma.js";

const agendamentoService = {

  async criar(dto) {

    // 🔎 Buscar serviço
    const servico = await prisma.servico.findUnique({
      where: { id: dto.servicoId }
    });

    if (!servico) {
      throw new Error("Serviço não encontrado");
    }

    // 📅 Converter data
    const dataFormatada = new Date(`${dto.data}T00:00:00`);

    // 🕐 Converter horaInicio
    const [hora, minuto] = dto.horaInicio.split(":").map(Number);
    const inicioMinutos = hora * 60 + minuto;

    const horaInicioDate = new Date(
      `1970-01-01T${String(hora).padStart(2, "0")}:${String(minuto).padStart(2, "0")}:00`
    );

    // 🕐 Calcular horaFim baseado na duração do serviço
    const fimMinutos = inicioMinutos + servico.duracaoMinutos;

    const horaFimDate = new Date(
      `1970-01-01T${String(Math.floor(fimMinutos / 60)).padStart(2, "0")}:${String(fimMinutos % 60).padStart(2, "0")}:00`
    );

    // 🚨 VERIFICAR CONFLITO DE HORÁRIO
    const conflito = await prisma.agendamento.findFirst({
      where: {
        data: dataFormatada,
        status: {
          not: "CANCELADO" // ignora cancelados
        },
        AND: [
          {
            horaInicio: {
              lt: horaFimDate
            }
          },
          {
            horaFim: {
              gt: horaInicioDate
            }
          }
        ]
      }
    });

    if (conflito) {
      throw new Error("Horário já está ocupado");
    }

    // ✅ Criar agendamento
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
      include: { servico: true },
      orderBy: {
        horaInicio: "asc"
      }
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