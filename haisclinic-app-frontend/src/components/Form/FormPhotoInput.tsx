import { type ChangeEvent, useEffect, useState } from 'react'
import { FiCamera } from 'react-icons/fi'

interface FormPhotoInputProps {
  id: string
  file?: File | null
  initialImageUrl?: string | null
  onChange: (file: File | null) => void
}

export function FormPhotoInput({
  id,
  file,
  initialImageUrl,
  onChange,
}: FormPhotoInputProps) {
  const [preview, setPreview] = useState<string | null>(null)

  useEffect(() => {
    if (!file) {
      setPreview(initialImageUrl ?? null)
      return
    }

    const previewUrl = URL.createObjectURL(file)
    setPreview(previewUrl)

    return () => URL.revokeObjectURL(previewUrl)
  }, [file, initialImageUrl])

  function handleChange(event: ChangeEvent<HTMLInputElement>) {
    onChange(event.target.files?.[0] ?? null)
  }

  return (
    <>
      <label
        htmlFor={id}
        className="flex h-16 w-16 cursor-pointer items-center justify-center overflow-hidden rounded-full border-2 border-white bg-white text-primary transition hover:border-button"
        title="Selecionar foto"
      >
        {preview ? (
          <img
            src={preview}
            alt="Foto selecionada"
            className="h-full w-full object-cover"
          />
        ) : (
          <FiCamera size={30} aria-hidden />
        )}
      </label>
      <input
        id={id}
        name={id}
        type="file"
        accept="image/*"
        onChange={handleChange}
        className="sr-only"
      />
    </>
  )
}
