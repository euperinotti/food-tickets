import useRouteMatch from "@/hooks/useRouteMatch";
import { SidebarItemProps } from "./types";

export const SidebarItem = ({
  icon,
  label,
  href,
  isOpen,
}: SidebarItemProps) => {
  const { isMatch } = useRouteMatch(href);
  const Icon = icon({
    size: isOpen ? "" : "22px",
    className: `${!isOpen && "w-full"} ${isMatch && "text-white"}`,
  });

  return (
    <li>
      <a
        href={href}
        className={`flex items-center p-2 text-gray-900 rounded-lg hover:bg-gray-100 transition-all duration-300 ${isMatch && "text-white bg-blue-500 hover:bg-blue-500"}`}
      >
        {Icon}
        {isOpen && <span className="ml-3">{label}</span>}
      </a>
    </li>
  );
};
