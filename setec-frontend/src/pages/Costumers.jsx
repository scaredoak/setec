import { useEffect, useState } from "react"
import costumerService from "../api/costumerService"

export default function Costumers() {
  const [costumers, setCostumers] = useState([])
  const [costumerForm, setCostumerForm] = useState({
    name: "",
    email: ""
  })

  useEffect(() => {
    costumerService.getAll()
      .then(res => {
        setCostumers(res.data)
      })
      .catch(e => {
        console.error("Erro: ", e)
      })
  }, [])

  const handleChange = (e) => {
    const { name, value } = e.target
    setCostumerForm(previous => ({...previous, [name]: value}))
  }

  const handleSubmit = async (e) => {
    e.preventDefault()

    try {
      await costumerService.create(costumerForm)
      const newCostumers = await costumerService.getAll()
      setCostumers(newCostumers.data)
      document.getElementById("costumer-submit-form").reset()
    } catch (e) {
      console.error(e)
    }
  }

  const handleSearch = (e) => {}

  return (
    <>
      <h1>Clientes</h1>
      <div>
        <form id="costumer-submit-form" onSubmit={handleSubmit}>
          <label>Nome: </label><input name="name" type="text" onChange={handleChange} required/>
          <br/>
          <br/>

          <label>Email: </label><input name="email" type="email" onChange={handleChange} required/>
          <br/>
          <br/>

          <button type="submit">Cadastrar cliente</button>
        </form>

        <br/>

        <table id="costumers">
          <thead>
            <tr>
              <th>ID</th>
              <th>NOME</th>
              <th>EMAIL</th>
            </tr>
          </thead>

          <tbody>
            {costumers.map(costumer => {
              return (
                <tr key={crypto.randomUUID()}>
                  <td>{costumer.id}</td>
                  <td>{costumer.name}</td>
                  <td>{costumer.email}</td>
                </tr>
              )
            })}
          </tbody>
        </table>
      </div>
    </>
  )
}
