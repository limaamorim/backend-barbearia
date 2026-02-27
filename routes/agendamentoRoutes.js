import { Router } from "express";
import controller from "../controller/agendamentoController.js";

const router = Router();

router.post("/", controller.criar);
router.get("/", controller.listar);
router.get("/data/:data", controller.listarPorData);
router.patch("/:id/confirmar", controller.confirmar);
router.put("/:id", controller.atualizar);
router.delete("/:id", controller.cancelar);

export default router;