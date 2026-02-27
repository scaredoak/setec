import { useEffect, useState } from "react"
import { Link } from "react-router-dom"
import orderService from "../api/orderService"

export default function Orders() {
  const [orders, setOrders] = useState([])
  const [ordersForm, setOrdersForm] = useState({
    costumer: {
      id: null
    },
    products: []
  })

  const initial = [
    { id: 1, type: "number" }
  ]
  const [inputArray, setInputArray] = useState(initial)

  useEffect(() => {
    orderService.getAll()
      .then(res => {
        setOrders(res.data)
      })
      .catch(err => {
        console.error(err)
      })
  }, [])

  function handleChangeCostumerId(e) {
    setOrdersForm(prev => ({ ...prev, costumer: { id: e.target.value } }))
  }

  function handleChangeProduct(e) {
    setOrdersForm(prev => ({ ...prev, products: [...prev.products, {id: e.target.value}] }))
  }

  async function handleSubmit(e) {
    e.preventDefault()
    console.log(ordersForm)

    try {
      await orderService.create(ordersForm)
      const newOrders = await orderService.getAll()
      setOrders(newOrders.data)
      document.getElementById("order-submit-form").reset()
    } catch (e) {
      console.error(e)
    }
  }

  function addInput(e) {
    setInputArray(arr => ([...arr, { type: "number" }]))
  }

  return (
    <>
      <h1>Pedidos</h1>
      <div>
        <label>ID do cliente: </label><input name="costumerId" type="number" onChange={handleChangeCostumerId} size="10" required />

        <form id="order-submit-form" onSubmit={handleSubmit}>
          {inputArray.map((item, i) => {
            return (
              <>
                <br />
                <label>ID do produto: </label>
                <input
                  id={`productIdInput-${i}`}
                  name="productId"
                  className="inputProduct inputProductId"
                  type={item.type}
                  onChange={handleChangeProduct}
                  size="10"
                  required
                />
              </>
            )
          })}
          <button type="button" onClick={addInput}>+</button>
          <br/>

          <br/>
          <button type="submit">Registrar pedido</button>
        </form>

        <br/>
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
