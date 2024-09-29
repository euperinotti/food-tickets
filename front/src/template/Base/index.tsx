import { BaseTemplateProps } from "./types";

export const BaseTemplate = ({ children, ...rest }: BaseTemplateProps) => {
  return (
    <div
      className={`bg-blue-900 min-h-dvh h-full text-slate-50 py-12 px-60 ${rest.className}`}
      {...rest}
    >
      <div className="flex flex-col justify-start items-start h-full w-full gap-12">
        <div className="flex flex-col justify-start items-start h-auto w-full gap-6">
          {children}
        </div>
      </div>
    </div>
  );
};
