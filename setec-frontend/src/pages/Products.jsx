import { useEffect, useState } from "react"
import productService from "../api/productService"

export default function Products() {
  const [products, setProducts] = useState([])
  const [productsForm, setProductsForm] = useState({
    description: "",
    price: null,
    stockAmount: null
  })

  useEffect(() => {
    productService.getAll()
      .then(res => {
        setProducts(res.data)
      })
      .catch(err => {
        console.error(err)
      })
  }, [])

  function handleChange(e) {
    const { name, value } = e.target
    setProductsForm(previous => ({...previous, [name]: value}))
  }

  async function handleSubmit(e) {
    e.preventDefault()

    try {
      await productService.create(productsForm)
      const newProducts = await productService.getAll()
      setProducts(newProducts.data)
      document.getElementById("product-submit-form").reset()
    } catch (e) {
      console.error(e)
    }
  }

  return (
    <>
      <h1>Produtos</h1>
      <div>
        <form id="product-submit-form" onSubmit={handleSubmit}>
          <label>Descrição: </label><input name="description" type="text" onChange={handleChange} required/>
          <br/>
          <br/>
          <label>Preço: </label><input name="price" type="number" min="0" step="0.01" onChange={handleChange} required/>
          <br/>

          <br/>
          <label>Quantidade no estoque: </label><input name="stockAmount" type="number" onChange={handleChange} required/>
          <br/>

          <br/>
          <button type="submit">Registrar produto</button>
        </form>

        <br/>

        <table id="products">
          <thead>
            <tr>
              <th>ID</th>
              <th>DESCRIÇÃO</th>
              <th>PREÇO</th>
              <th>QUANTIDADE EM ESTOQUE</th>
            </tr>
          </thead>

          <tbody>
            {products.map(product => {
              return (
                <tr key={crypto.randomUUID()}>
                  <td>{product.id}</td>
                  <td>{product.description}</td>
                  <td>{product.price}</td>
                  <td>{product.stockAmount}</td>
                </tr>
              )
            })}
          </tbody>
        </table>
      </div>
    </>
  )
}
