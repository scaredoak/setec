import api from './base'

async function getAll() {
  const response = await api.get('/costumer/all')
  return response
}

async function getById(id) {
  const response = await api.get(`/costumer/${id}`)
  return response
}

async function getByName(name) {
  const response = await api.get(`/costumer/name/${name}`)
  return response
}

async function create(data) {
  const response = await api.post('/costumer', data)
  return response
}

async function remove() {
  const response = await api.get(`/costumer/${id}`)
  return response
}

export default { getAll, getById, getByName, create, remove }
