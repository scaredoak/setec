import { BrowserRouter, Link, Route, Routes } from "react-router-dom"
import Costumers from "./pages/Costumers"
import Home from "./pages/Home"
import Products from "./pages/Products"
import Orders from "./pages/Orders"
import OrderDetails from "./pages/OrderDetails"


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
          <Route path="/clientes" element={<Costumers />} />
          <Route path="/produtos" element={<Products />} />
          <Route path="/pedidos" element={<Orders />} />
          <Route path="/pedidos/:orderId" element={<OrderDetails />} />
          <Route path="/about" element={<About />} />
        </Routes>
      </BrowserRouter>
    </>
  )
}

export default App
