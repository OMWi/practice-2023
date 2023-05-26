import {
  Drawer,
  Link,
  List,
  ListItem,
  ListItemText,
  Typography,
} from "@mui/material";

export default function MenuDrawer({ open, auth, setDrawerOpen }) {
  return (
    <Drawer
      open={open}
      anchor="top"
      PaperProps={{ sx: { backgroundColor: "primary.main" } }}
    >
      <List>
        <ListItem>
          <ListItemText>
            <Link href="/words" onClick={() => setDrawerOpen(false)}>
              <Typography variant="h6" textAlign="center">
                Words
              </Typography>
            </Link>
          </ListItemText>
        </ListItem>
        <ListItem>
          <ListItemText>
            <Link href="/word-lists" onClick={() => setDrawerOpen(false)}>
              <Typography variant="h6" textAlign="center">
                Lists
              </Typography>
            </Link>
          </ListItemText>
        </ListItem>
        <ListItem>
          <ListItemText>
            <Link href="/leaderboard" onClick={() => setDrawerOpen(false)}>
              <Typography variant="h6" textAlign="center">
                Leaderboard
              </Typography>
            </Link>
          </ListItemText>
        </ListItem>
        {auth && (
          <ListItem>
            <ListItemText>
              <Link href="/profile" onClick={() => setDrawerOpen(false)}>
                <Typography variant="h6" textAlign="center">
                  Profile
                </Typography>
              </Link>
            </ListItemText>
          </ListItem>
        )}
        {!auth && (
          <>
            <ListItem>
              <ListItemText>
                <Link href="/login" onClick={() => setDrawerOpen(false)}>
                  <Typography variant="h6" textAlign="center">
                    Login
                  </Typography>
                </Link>
              </ListItemText>
            </ListItem>
            <ListItem>
              <ListItemText>
                <Link href="/signup" onClick={() => setDrawerOpen(false)}>
                  <Typography variant="h6" textAlign="center">
                    Register
                  </Typography>
                </Link>
              </ListItemText>
            </ListItem>
          </>
        )}
        <ListItem>
          <ListItemText>
            <Link href="#" onClick={() => setDrawerOpen(false)}>
              <Typography variant="h6" textAlign="center">
                Close
              </Typography>
            </Link>
          </ListItemText>
        </ListItem>
      </List>
    </Drawer>
  );
}
