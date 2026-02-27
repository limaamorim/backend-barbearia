import servicoService from "../services/servicoService.js";

const controller = {

  async criar(req, res) {
    const servico = await servicoService.criar(req.body);
    res.status(201).json(servico);
  },

  async listar(req, res) {
    const lista = await servicoService.listar();
    res.json(lista);
  },

  async buscarPorId(req, res) {
    const servico = await servicoService.buscarPorId(req.params.id);
    res.json(servico);
  },

  async atualizar(req, res) {
    const servico = await servicoService.atualizar(
      req.params.id,
      req.body
    );
    res.json(servico);
  },

  async deletar(req, res) {
    await servicoService.deletar(req.params.id);
    res.status(204).send();
  }

};

export default controller;