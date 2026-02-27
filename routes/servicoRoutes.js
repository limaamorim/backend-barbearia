import express from "express";
import controller from "../controller/servicoController.js";

const router = express.Router();

router.post("/", controller.criar);
router.get("/", controller.listar);
router.get("/:id", controller.buscarPorId);
router.put("/:id", controller.atualizar);
router.delete("/:id", controller.deletar);

export default router;