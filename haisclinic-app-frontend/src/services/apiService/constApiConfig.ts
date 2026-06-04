interface serviceInt {
  [key: string]: string
}

export const SERVICE: serviceInt = {
  CORE: import.meta.env.VITE_CORE_API_URL ?? '',
}
