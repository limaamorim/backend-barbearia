import { Router } from "express";
import dashboardController from "../controller/dashboardController.js";

const router = Router();

router.get("/", dashboardController.resumo);

export default router;