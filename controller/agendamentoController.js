import agendamentoService from "../services/agendamentoService.js";

class AgendamentoController {

  async criar(req, res) {
    try {
      console.log("BODY:", req.body);

      const agendamento = await agendamentoService.criar(req.body);
      return res.status(201).json(agendamento);
    } catch (error) {
      console.log("ERRO:", error.message);
      return res.status(400).json({ message: error.message });
    }
  }

  async listar(req, res) {
    try {
      const agendamentos = await agendamentoService.listar();
      return res.json(agendamentos);
    } catch (err) {
      return res.status(500).json({ message: err.messsage })
    }

  }

  async listarPorData(req, res) {
    const { data } = req.params;
    const agendamentos = await agendamentoService.listarPorData(data);
    return res.json(agendamentos);
  }

  async confirmar(req, res) {
    await agendamentoService.confirmar(req.params.id);
    return res.status(204).send();
  }

  async cancelar(req, res) {
    await agendamentoService.cancelar(req.params.id);
    return res.status(204).send();
  }

  async atualizar(req, res) {
    try {
      const agendamento = await agendamentoService.atualizar(
        req.params.id,
        req.body
      );
      return res.json(agendamento);
    } catch (error) {
      return res.status(400).json({ message: error.message });
    }
  }
}

export default new AgendamentoController();