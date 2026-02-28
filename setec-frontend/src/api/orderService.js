import api from "./base"

async function getAll() {
  const response = await api.get("/order/all")
  return response
}

async function getById(id) {
  const response = await api.get(`/order/${id}`)
  return response
}

async function getByCostumerId(id) {
  const response = await api.get(`/order/by-costumer/${id}`)
  return response
}

async function create(data) {
  const response = await api.post("/order", data)
  return response
}

export default { getAll, getById, getByCostumerId, create }
