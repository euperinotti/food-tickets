import { HomeCardProps } from "./types";

export const HomeCard = ({ title, description, ...rest }: HomeCardProps) => {
  return (
    <div {...rest} className={`flex w-full rounded-lg border border-gray-200 bg-white flex-col max-w-sm shadow-none disabled:hover gap-2 ${rest.className}`}>
      <div className="flex w-full h-full flex-col justify-center gap-4 p-4">
        <span className="text-sm tracking-tight text-gray-400">{title}</span>
        <h5 className="text-4xl font-bold text-gray-900">{description}</h5>
      </div>
    </div>
  );
};
