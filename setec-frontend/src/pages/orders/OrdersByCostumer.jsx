import { useEffect, useState } from "react"
import { Link, useParams } from "react-router-dom"
import orderService from "../../api/orderService"
import costumerService from "../../api/costumerService"
import utils from "../../utils"

export default function OrdersByCostumer() {
  const { costumerId } = useParams()
  const [searchText, setSearchText] = useState("")
  const [orders, setOrders] = useState([])
  const [allOrdersTotal, setAllOrdersTotal] = useState(0)

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
        const data = res.data

        setAllOrdersTotal(
          utils.truncate(
            data.map(order =>
              order.products
                .reduce((acc, product) => acc + product.price, 0)
            ).reduce((acc, orderTotal) => acc + orderTotal, 0)
          )
        )
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
      <h1>Pedidos do cliente {costumerId} ({costumerName})</h1>

      <p><b>Valor total de pedidos: </b>R${allOrdersTotal}</p>

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
