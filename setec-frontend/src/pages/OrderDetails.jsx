import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import orderService from "../api/orderService";

export default function OrderDetails() {
  const { orderId } = useParams()
  const [products, setProducts] = useState([])
  const [priceTotal, setPriceTotal] = useState(null)

  useEffect(() => {
    orderService.getById(parseInt(orderId))
      .then(res => {
        const data = res.data
        setPriceTotal(data.products.reduce((acc, p) => acc + p.price, 0))

        // count products
        const count = data.products.reduce((acc, prod) => acc.set(prod.id, (acc.get(prod.id) || 0) + 1), new Map());
        const quantified = data.products.map(prod => ({ ...prod, quantity: count.get(prod.id) }));
        const noDuplicates = [...new Map(quantified.map(prod => [prod.id, prod])).values()];

        setProducts(noDuplicates)

      })
      .catch(err => {
        console.error(err)
      })

  }, [])

  return (
    <div>
      <h1>Detalhes do pedido {orderId}</h1>

      <p><b>Valor total: </b>R${priceTotal}</p>
      <table id="order-details">
        <thead>
          <tr>
            <th>ID</th>
            <th>DESCRIÇÃO</th>
            <th>PREÇO</th>
            <th>QUANTIDADE</th>
          </tr>
        </thead>

        <tbody>
          {products.map(product => {
            return (
              <tr key={crypto.randomUUID()}>
                <td>{product.id}</td>
                <td>{product.description}</td>
                <td>R$ {product.price}</td>
                <td>{product.quantity}</td>
              </tr>
            )
          })}
        </tbody>
      </table>
    </div>
  )
}
