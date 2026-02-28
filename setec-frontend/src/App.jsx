import { BrowserRouter, Link, Route, Routes } from "react-router-dom"
import Costumers from "./pages/Costumers"
import Home from "./pages/Home"
import Products from "./pages/Products"
import Orders from "./pages/Orders"
import OrderDetails from "./pages/OrderDetails"
import "./App.css"

function App() {
  return (
    <>
      <BrowserRouter>
        <nav>
          <Link to="/">Início</Link> |{" "}
          <Link to="/clientes">Clientes</Link> |{" "}
          <Link to="/produtos">Produtos</Link> |{" "}
          <Link to="/pedidos">Pedidos</Link>
        </nav>

        <Routes>
          <Route path="/" element={<Home />}/>
          <Route path="/clientes" element={<Costumers />} />
          <Route path="/produtos" element={<Products />} />
          <Route path="/pedidos" element={<Orders />} />
          <Route path="/pedidos/:orderId" element={<OrderDetails />} />
        </Routes>
      </BrowserRouter>
    </>
  )
}

export default App
