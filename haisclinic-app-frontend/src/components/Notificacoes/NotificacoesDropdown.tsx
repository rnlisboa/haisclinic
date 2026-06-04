import { FiBell } from 'react-icons/fi'
import { useNotificacoes } from '../../hooks/useNotificacoes'
import { formatarData } from '../../utils/dateUtils'

export function NotificacoesDropdown() {
  const {
    notificacoes,
    totalNaoLidas,
    isLoading,
    hasError,
    marcarComoLida,
  } = useNotificacoes()

  return (
    <div className="group relative">
      <button
        type="button"
        aria-label="Notificações"
        className="relative flex h-12 w-12 items-center justify-center rounded text-white transition hover:text-button focus:outline-none focus:ring-2 focus:ring-button focus:ring-offset-2 focus:ring-offset-secondary"
      >
        <FiBell size={30} aria-hidden />
        {totalNaoLidas > 0 && (
          <span className="absolute right-1 top-1 flex min-h-5 min-w-5 items-center justify-center rounded-full bg-red-500 px-1 text-xs font-extrabold text-white">
            {totalNaoLidas > 9 ? '9+' : totalNaoLidas}
          </span>
        )}
      </button>

      <div className="invisible absolute right-0 top-full z-50 mt-4 w-96 rounded bg-white text-primary opacity-0 shadow-2xl transition group-focus-within:visible group-focus-within:opacity-100 group-hover:visible group-hover:opacity-100">
        <div className="border-b border-primary/10 px-5 py-4">
          <h2 className="text-lg font-extrabold">Notificações</h2>
        </div>

        <div className="max-h-96 overflow-y-auto py-2">
          {isLoading && (
            <p className="px-5 py-6 text-sm font-semibold text-primary/70">
              Carregando notificações...
            </p>
          )}

          {!isLoading && hasError && (
            <p className="px-5 py-6 text-sm font-semibold text-red-600">
              Não foi possível carregar as notificações.
            </p>
          )}

          {!isLoading && !hasError && notificacoes.length === 0 && (
            <p className="px-5 py-6 text-sm font-semibold text-primary/70">
              Nenhuma notificação
            </p>
          )}

          {!isLoading &&
            !hasError &&
            notificacoes.map((notificacao) => (
              <button
                key={notificacao.id}
                type="button"
                onClick={() => marcarComoLida(notificacao)}
                className={`block w-full px-5 py-4 text-left transition hover:bg-button/10 ${
                  notificacao.lida ? 'bg-white' : 'bg-button/15'
                }`}
              >
                <div className="flex items-start justify-between gap-4">
                  <div>
                    <h3 className="text-sm font-extrabold">
                      {notificacao.titulo}
                    </h3>
                    <p className="mt-1 text-sm font-medium text-primary/80">
                      {notificacao.mensagem}
                    </p>
                    <time className="mt-2 block text-xs font-semibold text-primary/60">
                      {formatarData(notificacao.criadoEm)}
                    </time>
                  </div>

                  {!notificacao.lida && (
                    <span
                      aria-hidden
                      className="mt-1 h-2.5 w-2.5 shrink-0 rounded-full bg-red-500"
                    />
                  )}
                </div>
              </button>
            ))}
        </div>
      </div>
    </div>
  )
}
