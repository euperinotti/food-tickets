"use client";

import { HomeCardProps } from "./types";

export const HomeCard = ({ title, description }: HomeCardProps) => {
  return (
    <div className="flex w-[300px] rounded-lg border border-gray-200 bg-white flex-col max-w-sm shadow-none disabled:hover gap-2">
      <div className="flex w-full h-full flex-col justify-center gap-2 p-6">
        <span className="text-sm tracking-tight text-gray-600">{title}</span>
        <h5 className="text-3xl font-bold text-gray-900">{description}</h5>
      </div>
    </div>
  );
};
