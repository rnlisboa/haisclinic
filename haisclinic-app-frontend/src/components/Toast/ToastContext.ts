import { createContext } from 'react'

export type ToastVariant = 'success' | 'error' | 'info'

export interface ToastMessage {
  title: string
  description?: string
  variant?: ToastVariant
}

export interface ToastContextValue {
  showToast: (message: ToastMessage) => void
}

export const ToastContext = createContext<ToastContextValue | null>(null)
