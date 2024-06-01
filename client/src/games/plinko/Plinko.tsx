import { useEffect, useState, useRef } from "react";
import { BallManager } from "./classes/BallManager";
import axios from "axios";

export default function Plinko() {
  const [ballManager, setBallManager] = useState<BallManager>();
  const canvasRef = useRef<any>();

  useEffect(() => {
    if (canvasRef.current) {
      const ballManager = new BallManager(
        canvasRef.current as unknown as HTMLCanvasElement
      );
      setBallManager(ballManager);
    }
  }, [canvasRef]);

  return (
    <div>
      <canvas ref={canvasRef} width="800" height="800"></canvas>
      <button
        onClick={async () => {
          const response = await axios.post(
            `http://localhost:8080/api/plinko/play`,
            {
              data: 1,
            }
          );
          if (ballManager) {
            ballManager.addBall(response.data.point);
          }
        }}
      >
        Add ball
      </button>
    </div>
  );
}
