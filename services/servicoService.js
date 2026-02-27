import prisma from "../prisma.js";

const servicoService = {

  async criar(data) {
    return await prisma.servico.create({
      data
    });
  },

  async listar() {
    return await prisma.servico.findMany();
  },

  async buscarPorId(id) {
    return await prisma.servico.findUnique({
      where: { id: Number(id) }
    });
  },

  async atualizar(id, data) {
    return await prisma.servico.update({
      where: { id: Number(id) },
      data
    });
  },

  async deletar(id) {
    return await prisma.servico.delete({
      where: { id: Number(id) }
    });
  }

};

export default servicoService;