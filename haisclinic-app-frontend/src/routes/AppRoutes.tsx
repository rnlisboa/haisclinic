import { BrowserRouter, Route, Routes } from "react-router-dom";
import PacienteDetalhe from "../pages/paciente";
import Pacientes from "../pages/pacientes";
import Register from "../pages/register";

export function AppRoutes() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/cadastros" element={<Register />} />
        <Route path="/pacientes" element={<Pacientes />} />
        <Route path="/pacientes/:id" element={<PacienteDetalhe />} />
      </Routes>
    </BrowserRouter>
  );
}
