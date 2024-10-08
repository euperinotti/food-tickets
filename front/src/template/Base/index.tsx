import Sidebar from "@/components/ui/Sidebar";
import { BaseTemplateProps } from "./types";

export const BaseTemplate = ({ children }: BaseTemplateProps) => {
  return (
    <div className="flex justify-center items-center w-full h-dvh">
      <Sidebar />
      <main className="p-6 flex flex-1 flex-col h-full w-full gap-4 overflow-y-scroll">{children}</main>
    </div>
  );
};
