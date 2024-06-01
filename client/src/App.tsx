import "./App.css";
// import { Authentication } from "@/views/Authentication.tsx";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Simulation from "@/games/plinko/Simulation";
import Plinko from "@/games/plinko/Plinko";

function App() {
  return (
    <>
      {/* <Authentication /> */}
      <BrowserRouter>
        <Routes>
          <Route path="simulation" element={<Simulation />} />
          <Route path="plinko" element={<Plinko />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
