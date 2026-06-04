import type { AxiosPromise } from 'axios'
import type { NotificacaoDto } from '../dtos/notificacao.dto'
import { Api, SERVICE } from './apiService/apiConfig'

class NotificacaoService {
  private apiCore: Api
  private baseUrl: string

  constructor() {
    this.apiCore = new Api()
    this.baseUrl = SERVICE.CORE
  }

  public async buscarNotificacoes(): AxiosPromise<NotificacaoDto[]> {
    return await this.apiCore.axios.get(`${this.baseUrl}/notificacoes`)
  }

  public async marcarComoLida(id: string): AxiosPromise<NotificacaoDto> {
    return await this.apiCore.axios.patch(`${this.baseUrl}/notificacoes/${id}/lida`)
  }
}

export default NotificacaoService
