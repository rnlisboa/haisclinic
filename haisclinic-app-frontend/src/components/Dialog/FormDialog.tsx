import * as Dialog from '@radix-ui/react-dialog'
import type { ReactNode } from 'react'
import { FiX } from 'react-icons/fi'

interface FormDialogProps {
  open: boolean
  title: string
  children: ReactNode
  onOpenChange: (open: boolean) => void
}

export function FormDialog({
  open,
  title,
  children,
  onOpenChange,
}: FormDialogProps) {
  return (
    <Dialog.Root open={open} onOpenChange={onOpenChange}>
      <Dialog.Portal>
        <Dialog.Overlay className="fixed inset-0 z-[60] bg-black/50" />
        <Dialog.Content className="fixed left-1/2 top-1/2 z-[70] flex max-h-[calc(100vh-48px)] w-[min(800px,calc(100%-32px))] -translate-x-1/2 -translate-y-1/2 flex-col overflow-y-auto rounded bg-secondary px-12 py-8 text-white shadow-2xl">
          <div className="flex items-start justify-between">
            <Dialog.Title className="text-2xl font-extrabold">
              {title}
            </Dialog.Title>
            <Dialog.Close
              className="text-white transition hover:text-button"
              aria-label="Fechar"
            >
              <FiX size={28} aria-hidden />
            </Dialog.Close>
          </div>

          {children}
        </Dialog.Content>
      </Dialog.Portal>
    </Dialog.Root>
  )
}
