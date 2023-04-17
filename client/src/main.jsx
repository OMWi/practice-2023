import React from "react";
import ReactDOM from "react-dom/client";
import {
  Route,
  RouterProvider,
  createBrowserRouter,
  createRoutesFromElements,
  Link as RouterLink,
} from "react-router-dom";
import { CssBaseline, ThemeProvider, createTheme } from "@mui/material";

import Root from "./routes/root";
import ErrorPage from "./error-page";
import Index from "./routes/index";
import Login from "./routes/login";
import Register from "./routes/register";
import Words, { loader as wordsLoader } from "./routes/words";
import Word, { loader as wordLoader } from "./routes/word";
import Profile, { loader as profileLoader } from "./routes/profile";

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path="/" element={<Root />} errorElement={<ErrorPage />}>
      <Route errorElement={<ErrorPage />}>
        <Route index element={<Index />} />
        <Route path="login" element={<Login />} />
        <Route path="register" element={<Register />} />
        <Route path="profile" element={<Profile />} loader={profileLoader} />
        <Route path="words" element={<Words />} loader={wordsLoader} />
        <Route path="words/:wordsId" element={<Word />} loader={wordLoader} />
      </Route>
    </Route>,
  ),
);

const LinkBehavior = React.forwardRef((props, ref) => {
  const { href, ...other } = props;
  // Map href (MUI) -> to (react-router)
  return <RouterLink ref={ref} to={href} {...other} />;
});

const theme = createTheme({
  components: {
    MuiLink: {
      defaultProps: {
        component: LinkBehavior,
        underline: "none",
        color: "white",
        sx: {
          "&:hover": {
            color: "lightgray",
          },
        },
      },
      MuiButtonBase: {
        defaultProps: {
          LinkComponent: LinkBehavior,
        },
      },
    },
  },
});

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <RouterProvider router={router} />
    </ThemeProvider>
  </React.StrictMode>,
);
