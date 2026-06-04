import axios, { type AxiosInstance } from 'axios'
import { SERVICE } from './constApiConfig'

class Api {
  public axios: AxiosInstance

  constructor(serviceUrl: string = SERVICE.CORE) {
    this.axios = axios.create({
      baseURL: serviceUrl,
      headers: {
        'Content-Type': 'application/json',
        Accept: 'application/json',
      },
      timeout: 30000,
    })

    this.axios.interceptors.response.use(
      (response) => response,
      (error) => Promise.reject(error),
    )
  }
}

export { Api, SERVICE }
