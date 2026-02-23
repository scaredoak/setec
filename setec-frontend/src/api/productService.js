import api from "./base"

async function getAll() {
  const response = await api.get("/product/all")
  return response
}

async function getById(id) {
  const response = await api.get(`/product/{id}`)
  return response
}

async function create(data) {
  const response = await api.post("/product", data)
  return response
}

async function remove() {
  const response = await api.get(`/product/{id}`)
  return response
}

export default { getAll, getById, create, remove }
