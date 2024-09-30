import { forwardRef, MouseEvent, useRef } from "react";
import { DialogProps } from "./types";

export const Dialog = forwardRef(
  ({ children, ...rest }: DialogProps, ref: any) => {
    const containerRef = useRef<any>(null);
    const handleDialogClick = (e: MouseEvent<HTMLDialogElement>) => {
      if (
        e.currentTarget === e.target ||
        e.target == containerRef.current
      )
        e.currentTarget.close();
    };

    return (
      <dialog
        {...rest}
        className="min-w-full min-h-full h-dvh z-50 fixed bg-blend-overlay bg-gray-900 bg-opacity-40 transition-opacity m-0 p-0 right-0 "
        onClick={handleDialogClick}
        ref={ref}
      >
        <div
          className="w-full min-h-dvh h-full flex items-center justify-center m-0 p-0"
          ref={containerRef}
        >
          {children}
        </div>
      </dialog>
    );
  }
);
