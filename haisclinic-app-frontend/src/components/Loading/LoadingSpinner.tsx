interface LoadingSpinnerProps {
  label?: string
}

export function LoadingSpinner({ label = 'Carregando' }: LoadingSpinnerProps) {
  return (
    <div
      role="status"
      aria-label={label}
      className="flex min-h-[320px] w-full items-center justify-center"
    >
      <div className="h-16 w-16 animate-spin rounded-full border-4 border-white border-t-button" />
      <span className="sr-only">{label}</span>
    </div>
  )
}
