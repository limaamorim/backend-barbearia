import express from "express";
import cors from "cors";

import servicoRoutes from "./routes/servicoRoutes.js";
import agendamentoRoutes from "./routes/agendamentoRoutes.js";
import dashboardRoutes from "./routes/dashboardRoutes.js";

const app = express();

app.use(cors());
app.use(express.json());

app.get("/health", (req, res) => {
  res.send("ok");
});

app.use("/servicos", servicoRoutes);
app.use("/agendamentos", agendamentoRoutes);
app.use("/dashboard", dashboardRoutes);

const PORT = process.env.PORT || 3333;

app.listen(PORT, "localhost", () => {
  console.log(`Servidor rodando em http://localhost:${PORT}`);
});
