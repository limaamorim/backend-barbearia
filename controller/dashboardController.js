import dashboardService from "../services/dashboardService.js";

class DashboardController {
  async resumo(req, res) {
    try {
      const { inicio, fim } = req.query;

      if (!inicio || !fim) {
        return res.status(400).json({ message: "Datas obrigatórias" });
      }

      const resultado = await dashboardService.buscarResumo(inicio, fim);
      return res.json(resultado);
    

    } catch (error) {
      return res.status(500).json({ message: error.message });
    }
    
  }
}

export default new DashboardController();