import { useEffect, useState } from "react"
import { Link } from "react-router-dom"
import orderService from "../api/orderService"

export default function Orders() {
  const [orders, setOrders] = useState([])

  useEffect(() => {
    orderService.getAll()
      .then(res => {
        setOrders(res.data)
      })
      .catch(err => {
        console.error(err)
      })
  }, [])

  return (
    <>
      <h1>Pedidos</h1>
      <div>
        <table id="orders">
          <thead>
            <tr>
              <th>ID DO PEDIDO</th>
              <th>NOME DO CLIENTE</th>
              <th>VALOR</th>
              <th></th>
            </tr>
          </thead>

          <tbody>
            {orders.map(order => {
              const priceTotal = order.products.reduce((acc, prod) => acc + prod.price, 0)
              return (
                <tr key={crypto.randomUUID()}>
                  <td>{order.id}</td>
                  <td>{order.costumer.name}</td>
                  <td>R$ {priceTotal}</td>
                  <td><Link to={`/pedidos/${order.id}`}>produtos</Link></td>
                </tr>
              )
            })}
          </tbody>
        </table>
      </div>
    </>
  )
}
