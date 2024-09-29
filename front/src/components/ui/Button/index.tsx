"use client";

import { Button, ButtonProps } from "flowbite-react";

export interface FlowbiteButtonProps extends ButtonProps {
  title: string;
  icon?: {
    component: any;
    className?: string;
  };
}

export function FlowbiteButton({ title, icon, ...rest }: FlowbiteButtonProps) {
  const Component = icon?.component;
  const ComponentStyles = icon?.className;

  return (
    <Button color="blue" {...rest} className={`gap-4 ${rest.className}`}>
      {icon && <Component className={`h-full ${ComponentStyles}`} />}
      {title}
    </Button>
  );
}
