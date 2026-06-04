import { useCallback, useEffect, useMemo, useState } from 'react'
import type { NotificacaoDto } from '../dtos/notificacao.dto'
import NotificacaoService from '../services/notificacaoService'

const notificacaoService = new NotificacaoService()
const POLLING_INTERVAL = 30000

export function useNotificacoes() {
  const [notificacoes, setNotificacoes] = useState<NotificacaoDto[]>([])
  const [isLoading, setIsLoading] = useState(true)
  const [hasError, setHasError] = useState(false)

  const buscarNotificacoes = useCallback(async (showLoading = false) => {
    try {
      if (showLoading) {
        setIsLoading(true)
      }

      const response = await notificacaoService.buscarNotificacoes()
      setNotificacoes(response.data)
      setHasError(false)
    } catch {
      setHasError(true)
    } finally {
      setIsLoading(false)
    }
  }, [])

  useEffect(() => {
    buscarNotificacoes(true)

    const interval = window.setInterval(() => {
      buscarNotificacoes()
    }, POLLING_INTERVAL)

    return () => window.clearInterval(interval)
  }, [buscarNotificacoes])

  async function marcarComoLida(notificacao: NotificacaoDto) {
    if (notificacao.lida) {
      return
    }

    setNotificacoes((current) =>
      current.map((item) =>
        item.id === notificacao.id ? { ...item, lida: true } : item,
      ),
    )

    try {
      const response = await notificacaoService.marcarComoLida(notificacao.id)
      setNotificacoes((current) =>
        current.map((item) => (item.id === notificacao.id ? response.data : item)),
      )
      setHasError(false)
    } catch {
      setNotificacoes((current) =>
        current.map((item) =>
          item.id === notificacao.id ? { ...item, lida: false } : item,
        ),
      )
      setHasError(true)
    }
  }

  const totalNaoLidas = useMemo(
    () => notificacoes.filter((notificacao) => !notificacao.lida).length,
    [notificacoes],
  )

  return {
    notificacoes,
    totalNaoLidas,
    isLoading,
    hasError,
    marcarComoLida,
  }
}
