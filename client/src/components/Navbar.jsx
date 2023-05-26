import { useState } from "react";
import {
  AppBar,
  Avatar,
  Container,
  IconButton,
  Link,
  Stack,
  Toolbar,
  Typography,
  useMediaQuery,
  useTheme,
} from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";

import AuthService from "../services/auth";
import MenuDrawer from "./Drawer";

export default function Navbar({ auth }) {
  const theme = useTheme();
  const isSmallScreen = useMediaQuery(theme.breakpoints.down("sm"));

  const [drawerOpen, setDrawerOpen] = useState(false);

  return (
    <AppBar position="static">
      <Container maxWidth="lg">
        <Toolbar>
          <MenuDrawer
            open={drawerOpen}
            auth={auth}
            setDrawerOpen={setDrawerOpen}
          />
          {isSmallScreen && (
            <Stack
              direction="row"
              alignItems="center"
              sx={{
                "&:hover": {
                  cursor: "pointer",
                },
              }}
              onClick={() => setDrawerOpen(!drawerOpen)}
            >
              <IconButton>
                <MenuIcon />
              </IconButton>
              <Typography variant="h6">Menu</Typography>
            </Stack>
          )}

          {!isSmallScreen && (
            <Stack direction="row" alignItems="center" spacing={1.5}>
              <Link href="/words">
                <Typography variant="h6">Words</Typography>
              </Link>
              <Link href="/word-lists">
                <Typography variant="h6">Lists</Typography>
              </Link>
              <Link href="/leaderboard">
                <Typography variant="h6">Leaderboard</Typography>
              </Link>
            </Stack>
          )}

          <Stack
            direction="row"
            flexGrow="1"
            justifyContent="flex-end"
            alignItems="center"
            spacing={1.5}
          >
            {auth && (
              <Link href="/profile">
                <Stack direction="row" alignItems="center" spacing={1}>
                  <Avatar sx={{ bgcolor: "secondary.main" }}>
                    {AuthService.getCurrentUser().username[0].toUpperCase()}
                  </Avatar>
                  {!isSmallScreen && (
                    <Typography variant="h6">Profile</Typography>
                  )}
                </Stack>
              </Link>
            )}
            {!auth && (
              <>
                <Link href="/login">
                  <Typography variant="h6">Login</Typography>
                </Link>
                <Link href="/register">
                  <Typography variant="h6">Register</Typography>
                </Link>
              </>
            )}
          </Stack>
        </Toolbar>
      </Container>
    </AppBar>
  );
}
