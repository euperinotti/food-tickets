import { ReactNode } from "react";

export interface InputContainerProps {
  label: string;
  children: ReactNode;
}

export const InputContainer = ({
  children,
  label,
  ...rest
}: InputContainerProps) => {
  return (
    <div className="flex flex-col gap-2" {...rest}>
      <label className="font-semibold">{label}</label>
      {children}
    </div>
  );
};
