import { BrowserRouter, Link, Route, Routes } from "react-router-dom"
import Clients from "./pages/Clients"
import Home from "./pages/Home"
import Products from "./pages/Products"

function About() {
  return <h1>About page!</h1>
}

function App() {
  return (
    <>
      <BrowserRouter>
        <nav>
          <Link to="/">Início</Link> |{" "}
          <Link to="/clientes">Clientes</Link> |{" "}
          <Link to="/produtos">Produtos</Link> |{" "}
          <Link to="/pedidos">Pedidos</Link> |{" "}
          <Link to="/about">About</Link>
        </nav>

        <Routes>
          <Route path="/" element={<Home />}/>
          <Route path="/clientes" element={<Clients />} />
          <Route path="/produtos" element={<Products />} />
          <Route path="/pedidos" element={<Clients />} />
          <Route path="/about" element={<About />} />
        </Routes>
      </BrowserRouter>
    </>
  )
}

export default App
