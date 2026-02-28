function truncate(num, decimals = 2) {
  const scale = 10 ** decimals
  return Math.trunc(num * scale) / scale
}

export default { truncate }
