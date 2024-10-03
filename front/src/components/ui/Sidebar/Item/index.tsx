import useRouteMatch from "@/hooks/useRouteMatch";
import Link from "next/link";
import { SidebarItemProps } from "./types";

export const SidebarItem = ({
  icon,
  label,
  href,
  isOpen,
  ...rest
}: SidebarItemProps) => {
  const { isMatch } = useRouteMatch(href);
  const Icon = icon({
    size: isOpen ? "" : "22px",
    className: `${!isOpen && "w-full"} ${isMatch && "text-white"}`,
  });

  return (
    <li>
      <Link
        {...rest}
        href={href}
        className={`flex items-center p-2 rounded-lg transition-all duration-300 ${
          isMatch
            ? "text-white bg-blue-500 hover:bg-blue-500"
            : "text-gray-900 rounded-lg hover:bg-gray-100"
        }`}
      >
        {Icon}
        {isOpen && <span className="ml-3">{label}</span>}
      </Link>
    </li>
  );
};
