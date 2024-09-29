"use client";

import { Card } from "flowbite-react";

export interface FlowbiteCardProps {
  title: string;
  description: string
}

export const FlowbiteCard = ({ title, description }: FlowbiteCardProps) => {
  return (
    <Card href="#" className="max-w-sm shadow-none disabled:hover">
      <span className="text-sm tracking-tight text-gray-600">
        {title}
      </span>
      <h5 className="text-3xl font-bold text-gray-900">
        {description}
      </h5>
    </Card>
  );
};
