import type { AxiosPromise } from 'axios'
import type { ProfissionalDto } from '../dtos/profissional.dto'
import { Api, SERVICE } from './apiService/apiConfig'

class ProfissionalService {
  private apiCore: Api
  private baseUrl: string

  constructor() {
    this.apiCore = new Api()
    this.baseUrl = SERVICE.CORE
  }

  public async buscarProfissionais(): AxiosPromise<ProfissionalDto[]> {
    return await this.apiCore.axios.get(`${this.baseUrl}/profissionais`)
  }
}

export default ProfissionalService
