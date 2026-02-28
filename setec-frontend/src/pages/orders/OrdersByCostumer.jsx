import { useEffect, useState } from "react"
import { Link, useParams } from "react-router-dom"
import orderService from "../../api/orderService"
import costumerService from "../../api/costumerService"

export default function OrdersByCostumer() {
  const { costumerId } = useParams()
  const [searchText, setSearchText] = useState("")
  const [orders, setOrders] = useState([])

  const [costumerName, setCostumerName] = useState(null)

  useEffect(() => {
    costumerService.getById(costumerId)
      .then(res => {
        setCostumerName(res.data.name)
      })
      .catch(err => {
        console.error(err)
      })

    orderService.getByCostumerId(costumerId)
      .then(res => {
        setOrders(res.data)
      })
      .catch(err => {
        console.error(err)
      })
  }, [])

  const handleSearch = (e) => {
    setSearchText(e.target.value)
  }

  return (
    <>
      <h1>Pedidos do cliente {costumerName} (ID {costumerId})</h1>
      <div>
        <br />
        <input type="text" placeholder="Pesquisar (ID do pedido)" onChange={handleSearch} />

        <table id="orders">
          <thead>
            <tr>
              <th>ID</th>
              <th>NOME DO CLIENTE</th>
              <th>VALOR</th>
              <th>DETALHES</th>
            </tr>
          </thead>

          <tbody>
            {orders
              .filter(order => {
                if (searchText)
                  return searchText == `${order.id}`
                else
                  return true
              })
              .map(order => {
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
