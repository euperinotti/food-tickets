import { usePathname } from "next/navigation";
import { useEffect, useState } from "react";

function useRouteMatch(targetRoute: string) {
  const path = usePathname();
  const [isMatch, setIsMatch] = useState(false);

  useEffect(() => {
    setIsMatch(path === targetRoute);
  }, [path, targetRoute]);

  return { isMatch };
}

export default useRouteMatch;
