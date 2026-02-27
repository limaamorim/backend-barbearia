import express from "express";
import cors from "cors";

import servicoRoutes from "./routes/servicoRoutes.js";
import agendamentoRoutes from "./routes/agendamentoRoutes.js";
import dashboardRoutes from "./routes/dashboardRoutes.js";


const app = express();

app.use(cors());
app.use(express.json());

app.use("/servicos", servicoRoutes);
app.use("/agendamentos", agendamentoRoutes);
app.use("/dashboard", dashboardRoutes);

const PORT = process.env.PORT || 3333;

app.listen(PORT, "0.0.0.0", () => {
  console.log(`Servidor rodando na porta ${PORT}`);
});
