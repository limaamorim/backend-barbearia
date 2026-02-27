import prisma from "../prisma.js";

const dashboardService = {

    async buscarResumo(inicio, fim) {

        if (!inicio || !fim) {
            throw new Error("Datas inválidas");
        }

        const dataInicio = new Date(`${inicio}T00:00:00`);
        const dataFim = new Date(`${fim}T23:59:59`);

        if (isNaN(dataInicio) || isNaN(dataFim)) {
            throw new Error("Formato de data inválido");
        }

        const confirmados = await prisma.agendamento.count({
            where: {
                status: "CONFIRMADO",
                data: {
                    gte: dataInicio,
                    lte: dataFim
                }
            }
        });

        const agendamentosConfirmados = await prisma.agendamento.findMany({
            where: {
                status: "CONFIRMADO",
                data: {
                    gte: dataInicio,
                    lte: dataFim
                }
            },
            include: { servico: true }
        });

        const faturamento = agendamentosConfirmados.reduce(
            (total, ag) => total + Number(ag.servico.preco),
            0
        );

        return {
            cortesConfirmados: confirmados,
            faturamento
        };
    }
};

export default dashboardService;