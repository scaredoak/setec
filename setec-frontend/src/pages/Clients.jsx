import { useEffect, useState } from "react"
import clientService from "../api/clientService"

export default function Clients() {
  const [costumers, setCostumers] = useState([])
  const [costumerForm, setCostumerForm] = useState({
    name: "",
    email: ""
  })

  useEffect(() => {
    clientService.getAll()
      .then(res => {
        setCostumers(res.data)
      })
      .catch(e => {
        console.error("Erro: ", e)
      })
  }, [])

  function handleChange(e) {
    const { name, value } = e.target
    setCostumerForm(previous => ({...previous, [name]: value}))
  }

  async function handleSubmit(e) {
    e.preventDefault()

    try {
      await clientService.create(costumerForm)
      const newCostumers = await clientService.getAll()
      setCostumers(newCostumers.data)
      document.getElementById("client-submit-form").reset()
    } catch (e) {
      console.error(e)
    }
  }

  return (
    <>
      <h1>Clientes</h1>
      <div>
        <form id="client-submit-form" onSubmit={handleSubmit}>
          <label>Nome: </label><input name="name" type="text" onChange={handleChange} required/><br/>

          <label>Email: </label><input name="email" type="email" onChange={handleChange} required/>
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
            {costumers.map(client => {
              return (
                <tr key={crypto.randomUUID()}>
                  <td>{client.id}</td>
                  <td>{client.name}</td>
                  <td>{client.email}</td>
                </tr>
              )
            })}
          </tbody>
        </table>
      </div>
    </>
  )
}
