import { useEffect, useState } from "react"
import productService from "../api/productService"

export default function Products() {
  const [searchText, setSearchText] = useState("")
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

  const handleChange = (e) => {
    const { name, value } = e.target
    setProductsForm(previous => ({ ...previous, [name]: value }))
  }

  const handleSubmit = async (e) => {
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

  const handleSearch = (e) => {
    setSearchText(e.target.value.toLowerCase())
  }

  return (
    <>
      <h1>Produtos</h1>
      <div>
        <form id="product-submit-form" onSubmit={handleSubmit}>
          <label>Descrição: </label><input name="description" type="text" onChange={handleChange} required />
          <br />
          <br />
          <label>Preço: </label><input name="price" type="number" min="0" step="0.01" onChange={handleChange} required />
          <br />

          <br />
          <label>Quantidade no estoque: </label><input name="stockAmount" type="number" onChange={handleChange} required />
          <br />

          <br />
          <button type="submit">Registrar produto</button>
        </form>

        <br />
        <input type="text" placeholder="Pesquisar (ID ou descrição)" onChange={handleSearch} />

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
            {products
              .filter(product => {
                if (searchText) {
                  const description = product.description.toLowerCase()
                  const id = `${product.id}`
                  return description.includes(searchText) || searchText === id
                } else {
                  return true
                }
              })
              .map(product => {
                return (
                  <tr key={crypto.randomUUID()}>
                    <td>{product.id}</td>
                    <td>{product.description}</td>
                    <td>R$ {product.price}</td>
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
