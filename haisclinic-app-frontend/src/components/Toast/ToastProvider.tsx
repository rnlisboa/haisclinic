import * as Toast from '@radix-ui/react-toast'
import {
  type ReactNode,
  useCallback,
  useState,
} from 'react'
import {
  ToastContext,
  type ToastMessage,
  type ToastVariant,
} from './ToastContext'

const variantClasses: Record<ToastVariant, string> = {
  success: 'border-button',
  error: 'border-red-400',
  info: 'border-white',
}

export function ToastProvider({ children }: { children: ReactNode }) {
  const [open, setOpen] = useState(false)
  const [message, setMessage] = useState<ToastMessage>({
    title: '',
    variant: 'info',
  })

  const showToast = useCallback((nextMessage: ToastMessage) => {
    setOpen(false)
    window.setTimeout(() => {
      setMessage({
        variant: 'info',
        ...nextMessage,
      })
      setOpen(true)
    }, 100)
  }, [])

  return (
    <ToastContext.Provider value={{ showToast }}>
      <Toast.Provider swipeDirection="right">
        {children}
        <Toast.Root
          open={open}
          onOpenChange={setOpen}
          className={`rounded border-l-4 bg-secondary px-5 py-4 text-white shadow-xl ${
            variantClasses[message.variant ?? 'info']
          }`}
        >
          <Toast.Title className="text-base font-extrabold">
            {message.title}
          </Toast.Title>
          {message.description && (
            <Toast.Description className="mt-1 text-sm text-white/80">
              {message.description}
            </Toast.Description>
          )}
        </Toast.Root>
        <Toast.Viewport className="fixed bottom-6 right-6 z-[100] w-[360px] max-w-[calc(100vw-48px)] outline-none" />
      </Toast.Provider>
    </ToastContext.Provider>
  )
}
